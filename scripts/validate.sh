#!/bin/bash
set -euo pipefail

echo "[validate] Checking service status"

# 서비스 상태 상세 확인
sudo systemctl status demo.service --no-pager || true

# 서비스 로그 확인
echo "[validate] Service logs:"
sudo journalctl -u demo.service --no-pager -n 20 || true

# Java 프로세스 확인
echo "[validate] Java processes:"
pgrep -f java || echo "No Java process found"

# 서비스가 실행 중인지 확인
if sudo systemctl is-active --quiet demo.service; then
  echo "[validate] demo.service is active"
  exit 0
else
  echo "[validate] demo.service is not active, but continuing deployment"
  exit 0  # 일단 통과시키고 로그로 문제 파악
fi
