# API Cambista Banco
Proyecto basico de dockerización usando un proyecto spring boot
## Descripción
En este proyecto se identificará el endpoint de <code>/transform</code>.
## Desplegar Docker
Comandos:

<code>cambista % docker build -t "mibanco-cambista-docker" .</code>

<code>docker run --name mibanco-cambista-docker-init -p 8081:8080 mibanco-cambista-docker:latest</code>