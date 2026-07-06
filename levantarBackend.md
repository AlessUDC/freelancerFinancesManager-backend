# Crear .env desde la plantilla
cp .env.example .env
# Edita .env con tus credenciales reales

# Levantar base de datos y backend
docker compose up

# Si deseas puedes levantar el servicio en segundo plano
docker compose up -d

# Si hiciste cambios en el código Java
docker compose up --build
# Si usas este modo y quieres ver qué está pasando con tu aplicación en cualquier momento, puedes revisar los logs con:
docker compose logs -f app

# Apagar todo
docker compose down

# Cada vez que cambies aplication.properties,Dockerfile o código Java, reconstruye la imagen con:
docker compose down
# Recompila el JAR desde cero
docker compose build --no-cache app
# Levanta los servicios en segundo plano
docker compose up -d

# La app estará en http://localhost:8080
# PostgreSQL expuesto en localhost:5444 (configurable en .env)
