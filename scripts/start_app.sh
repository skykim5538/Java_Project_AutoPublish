#!/bin/bash
set -euxo pipefail

echo "[start_app] Starting application setup"

# systemd 서비스 파일 복사 (파일이 이미 배포된 후)
if [ -f /home/ec2-user/systemd/demo.service ]; then
  echo "[start_app] Copying systemd service file"
  sudo cp /home/ec2-user/systemd/demo.service /etc/systemd/system/demo.service
  sudo systemctl daemon-reload
  echo "[start_app] systemd service file copied and daemon reloaded"
else
  echo "[start_app] ERROR: /home/ec2-user/systemd/demo.service not found"
  echo "[start_app] Listing /home/ec2-user/ contents:"
  ls -la /home/ec2-user/ || true
  echo "[start_app] Listing /home/ec2-user/systemd/ contents:"
  ls -la /home/ec2-user/systemd/ || true
  exit 1
fi

echo "[start_app] Enabling and starting service"
sudo systemctl enable demo.service
sudo systemctl restart demo.service
sleep 2
sudo systemctl status demo.service --no-pager || true
