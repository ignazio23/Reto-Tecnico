import http from 'k6/http';
import { check } from 'k6';
import { SharedArray } from 'k6/data';
import exec from 'k6/execution';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';
import { textSummary } from 'https://jslib.k6.io/k6-summary/0.0.2/index.js';
import { htmlReport } from 'https://raw.githubusercontent.com/benc-uk/k6-reporter/3.0.4/dist/bundle.js';

const BASE_URL = __ENV.BASE_URL || 'https://fakestoreapi.com';
const TPS = Number(__ENV.TPS || 25);
const DURATION = __ENV.DURATION || '5m';

// usuarios se leen una sola vez y se comparten entre los VUs
const users = new SharedArray('users', () =>
  papaparse.parse(open('../data/users.csv'), {
    header: true,
    skipEmptyLines: true,
    transformHeader: (h) => h.trim(),
    transform: (v) => v.trim(),
  }).data
);

// configuraciones de las condiciones
export const options = {
  scenarios: {
    login: {
      executor: 'constant-arrival-rate',
      rate: TPS,
      timeUnit: '1s',
      duration: DURATION,
      preAllocatedVUs: 50,
      maxVUs: 200,
    },
  },
  thresholds: {
    http_req_duration: ['p(95)<1500'],            // Percentil 95 se espera una duración menor a 1.5 s
    http_req_failed: ['rate<0.03'],               // La tasa de fallido debe ser menor al 3%
    checks: ['rate>0.97'],                        // La tasa de éxito debe ser mayor al 97%
    http_reqs: ['rate>=20'],                      // El promedio de request debe ser al menos 20 requests por segundo
  },
};

// definición de la lógica a ejecutar
export default function () {
  const user = users[exec.scenario.iterationInTest % users.length];

  const payload = JSON.stringify({ username: user.user, password: user.passwd });
  const params = {
    headers: { 'Content-Type': 'application/json' },
    timeout: '60s',
    tags: { name: 'login' },
  };

  const res = http.post(`${BASE_URL}/auth/login`, payload, params);

  check(res, {
    'status es 201': (r) => r.status === 201,                          // se usa este code status porque el login responde 201 (Created), no 200
    'devuelve token': (r) => {
      try { return !!r.json('token'); } catch (e) { return false; }
    },
    'respuesta < 1.5 s': (r) => r.timings.duration < 1500,             // tiempo de validación solicitado
  });
}

// configuración de reportes
export function handleSummary(data) {
  const texto = textSummary(data, { indent: ' ', enableColors: false });
  return {
    stdout: texto,
    'reports/summary.txt': texto,
    'reports/summary.json': JSON.stringify(data, null, 2),
    'reports/resumen.html': htmlReport(data, { title: 'Prueba de carga - Login' }),
  };
}