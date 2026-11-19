Feature: Ciclo de vida de un usuario en Petstore (/user)

  Background:
    * url baseUrl
    * header Accept = 'application/json'

    * def sufijo = '' + java.lang.System.currentTimeMillis()
    * def idUsuario = java.lang.Long.parseLong(sufijo)
    * def username = 'qa_user_' + sufijo
    * def nombreInicial = 'Luis'
    * def correoInicial = 'luis.' + sufijo + '@example.com'
    * def nombreNuevo = 'Luis Suarez'
    * def correoNuevo = 'luis.suarez.' + sufijo + '@example.com'

    * def usuario =
      """
      {
        "id": "#(idUsuario)",
        "username": "#(username)",
        "firstName": "#(nombreInicial)",
        "lastName": "Suarez",
        "email": "#(correoInicial)",
        "password": "Luis*2026",
        "phone": "59812345678",
        "userStatus": 1
      }
      """

  Scenario: Crear, buscar, actualizar, buscar y eliminar un usuario

    # 1. Crear un usuario
    Given path 'user'
    And request usuario
    When method post
    Then status 200
    And match header Content-Type contains 'application/json'
    And match response == { code: 200, type: 'unknown', message: '#(sufijo)' }

    # 2. Buscar el usuario creado
    Given path 'user', username
    When method get
    Then status 200
    And match response == usuario

    # 3. Actualizar el nombre y el correo del usuario
    * def usuarioActualizado = karate.merge(usuario, { firstName: nombreNuevo, email: correoNuevo })
    Given path 'user', username
    And request usuarioActualizado
    When method put
    Then status 200
    And match response == { code: 200, type: 'unknown', message: '#(sufijo)' }

    # 4. Buscar el usuario actualizado
    Given path 'user', username
    When method get
    Then status 200
    And match response.firstName == nombreNuevo
    And match response.email == correoNuevo
    And match response.firstName != nombreInicial
    And match response.email != correoInicial
    And match response == usuarioActualizado

    # 5. Eliminar el usuario
    Given path 'user', username
    When method delete
    Then status 200
    And match response == { code: 200, type: 'unknown', message: '#(username)' }

    # Verificar que el usuario ya no exista
    Given path 'user', username
    When method get
    Then status 404
    And match response == { code: 1, type: 'error', message: 'User not found' }