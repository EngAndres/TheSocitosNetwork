/* Login */
SELECT COUNT(*) AS "existe" 
FROM usuario
WHERE nickname="casierrav" AND contrasenia="123456";

/* Informacion del Usuario */
SELECT usuario.id_usuario AS id, usuario.nombre, usuario.nickname,
		usuario.fec_nacimiento, usuario.descripcion, usuario.email,
        ubicacion.nombre AS "ubicacion", zona_horaria.nombre AS "zona"
FROM usuario
JOIN ubicacion ON usuario.ubicacion_fk=ubicacion.id_ubicacion
JOIN zona_horaria ON usuario.zona_horaria_fk=zona_horaria.id_zona;

/* Novedades del Usuario */
SELECT id_novedad AS id, contenido, fecha 
FROM novedad
WHERE usuario_fk=1;

/* Novedades de otros usuarios */
SELECT id_novedad AS id, contenido, fecha 
FROM novedad
WHERE usuario_fk!=1;

/* Usuarios asociados a un hobby por id */
SELECT usuarios.id_usuario AS id, usuario.nombre
FROM usuario
JOIN hobby_usuario ON hobby_usuario.usuario_fk=usuario.id_usuario
WHERE hobby_usuario.hobby_fk=1;

/* Usuarios asociados a un hobby por nombre */
SELECT usuario.id_usuario AS id, usuario.nombre
FROM usuario
JOIN hobby_usuario ON hobby_usuario.usuario_fk=usuario.id_usuario
JOIN hobby ON hobby_usuario.hobby_fk=hobby.id_hobby
WHERE hobby.nombre="Futbol";

/* Usuarios por ubicacion */
SELECT usuario.id_usuario AS id, usuario.nombre
FROM usuario
WHERE ubicacion_fk=1;

/* Usuarios por hobby y ubicacion */
SELECT usuario.id_usuario AS id, usuario.nombre
FROM usuario
JOIN hobby_usuario ON hobby_usuario.usuario_fk=usuario.id_usuario
WHERE usuario.ubicacion_fk=1 AND hobby_usuario.hobby_fk=2;

/* Novedades por rango de fechas */
SELECT novedad.id_novedad AS id, novedad.contenido, 
	   novedad.fecha, usuario.nombre AS nombre
FROM novedad
JOIN usuario ON novedad.usuario_fk=usuario.id_usuario
WHERE novedad.fecha>="2022-08-01" AND novedad.fecha<="2022-08-30";

/* Novedades por palabra clave en el contenido */
SELECT novedad.id_novedad AS id, novedad.contenido, 
	   novedad.fecha, usuario.nombre AS nombre
FROM novedad
JOIN usuario ON novedad.usuario_fk=usuario.id_usuario
WHERE novedad.contenido LIKE "%clave%"
ORDER BY novedad.fecha
LIMIT 10;

/* Generar la lista de paises */
SELECT id_pais AS id, nombre FROM pais;

/* Generar la lista de ubicaciones por pais */
SELECT id_ubicacion AS id, nombre FROM ubicacion WHERE pais_fk=1;

SELECT ubicacion.id_ubicacion AS "id", ubicacion.nombre AS "ubicacion", pais.nombre AS "pais"
FROM ubicacion
JOIN pais ON ubicacion.pais_fk=pais.id_pais;

/* Generar la lista de zonas horarias */
SELECT id_zona AS id, nombre, variacion FROM zona_horaria;

/* Generar la lista de hobbies */
SELECT id_hobby AS id, nombre FROM hobby;