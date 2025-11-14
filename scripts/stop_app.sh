#!/bin/bash

set -eux  # pipefail 제거

# 서비스가 존재하는지 먼저 확인
if systemctl list-unit-files | grep -q demo.service; then
  if systemctl is-active --quiet demo.service; then
    echo "[stop_app] Stopping demo.service"
    sudo systemctl stop demo.service
  else
    echo "[stop_app] demo.service exists but not running"
  fi
else
  echo "[stop_app] demo.service does not exist (first deployment)"
fi

exit 0



#코드가 너무 엄격
#set -euxo pipefail
#if systemctl is-active --quiet demo.service; then
#  sudo systemctl stop demo.service
#fi
