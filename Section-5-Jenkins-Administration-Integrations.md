
# Design and Implement CI/CD Pipelines using Jenkins
## Sections 5 (Hands-On Scripts)

---

## 5.6 Docker Integration

### Dockerfile
```dockerfile
FROM alpine:latest
WORKDIR /app
COPY . .
CMD ["echo", "Docker Image Built Successfully from Jenkins Workspace"]
```

---

## 5.7 Ansible Integration

### Ansible Playbook
```yaml
---
- name: Jenkins Ansible Demo
  hosts: web
  become: yes

  tasks:
    - name: Create demo file
      file:
        path: /tmp/jenkins-ansible-demo.txt
        state: touch

    - name: Write content to file
      copy:
        content: "Deployed by Jenkins using Ansible"
        dest: /tmp/jenkins-ansible-demo.txt
```

---

## 5.9 Teams Notification Script
```bash
curl -X POST "$TEAMS_WEBHOOK_URL" -H "Content-Type: application/json" -d '{"text": "Jenkins Teams-Notify-Demo Freestyle Job Build Complete!"}'
```

---

