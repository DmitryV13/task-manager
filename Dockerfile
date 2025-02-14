FROM postgres:latest
LABEL authors="dmitry voicu 2025"
EXPOSE 5432
VOLUME /var/lib/postgresql/data
CMD ["postgres"]


