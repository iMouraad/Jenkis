# Informe Técnico: Implementación de CI/CD con Jenkins y Kubernetes en Azure
**Tema:** TA-INFORME  
**Proyecto:** Microservicio de Gestión de Usuarios (`ms-usuarios`)  
**Estudiante:** iMouraad (Mouraaad)

---

## 1. Descripción del Proyecto
Este informe documenta la configuración de un ecosistema de Integración Continua y Despliegue Continuo (CI/CD) para un microservicio desarrollado en Spring Boot. Se ha migrado de una arquitectura monolítica/Docker-Compose hacia una orquestación profesional utilizando **Kubernetes** sobre infraestructura de **Microsoft Azure**.

---

## 2. Configuración CI/CD con Jenkins
Se ha implementado un Pipeline Declarativo mediante un archivo `Jenkinsfile` en la raíz del proyecto. Este pipeline automatiza el ciclo de vida del software:

- **Stage: Build**: Compilación del código fuente Java 21 utilizando Maven (`./mvnw clean package`).
- **Stage: Docker Build**: Creación de la imagen de contenedor utilizando el `Dockerfile` del proyecto.
- **Stage: Push**: Carga de la imagen al registro público **Docker Hub** (`mouraaad/ms-usuarios:latest`).
- **Stage: Deploy**: Orquestación del despliegue en el clúster de Kubernetes mediante la aplicación de manifiestos YAML.

---

## 3. Implementación de Seguridad: GitHub Secrets
Para garantizar la integridad y seguridad de la infraestructura, se implementó el uso de **GitHub Secrets**. Se evitó el "hardcoding" de credenciales sensibles, utilizando variables de entorno protegidas en el repositorio:

- **`AZURE_SSH_KEY`**: Llave privada RSA (`.pem`) para el acceso seguro al servidor de Azure.
- **`AZURE_IP`**: Dirección IP pública de la instancia en Azure (`20.169.91.166`).
- **`DOCKER_PASSWORD`**: Token de acceso seguro para la autenticación en Docker Hub.

*Esta práctica cumple con los estándares de seguridad DevSecOps, protegiendo la infraestructura de accesos no autorizados.*

---

## 4. Implementación de Kubernetes en Azure
El despliegue se realizó sobre una instancia de **Azure Virtual Machine** configurada como un clúster de **Kubernetes (K3s)**. 

### Arquitectura de Kubernetes:
Se crearon los siguientes recursos en el clúster (ubicados en la carpeta `/k8s`):

1.  **Deployment `ms-usuarios-db`**: Instancia de PostgreSQL 16 para la persistencia de datos.
2.  **Service `ms-usuarios-db`**: Servicio interno (ClusterIP) para la comunicación entre la app y la DB.
3.  **Deployment `ms-usuarios-app`**: El microservicio Spring Boot escalable.
4.  **Service `ms-usuarios-app`**: Expone la aplicación mediante un puerto de red (NodePort/LoadBalancer).

### Verificación del Estado:
El sistema se encuentra operativo y los recursos han sido validados con el comando:
```bash
sudo kubectl get all
```
**Resultado esperado:**
- Pods en estado `Running`.
- Servicios asignados correctamente.

---

## 5. Conclusiones
La implementación cumple con los tres pilares solicitados:
1.  **Automatización** mediante Jenkins.
2.  **Seguridad** mediante el manejo de secretos en GitHub.
3.  **Escalabilidad y Orquestación** mediante el uso de Kubernetes en la nube de Azure.

---
*Documento generado para la entrega del Trabajo Autónomo.*
