# NGINX-Image für statische HTML-Auslieferung
FROM nginx:alpine

# Kopiere statische Inhalte (Passe ggf. den Pfad an, z.B. build, dist, etc.)
COPY html /usr/share/nginx/html

# Kopiere die NGINX-Konfiguration
COPY nginx.conf /etc/nginx/conf.d/default.conf