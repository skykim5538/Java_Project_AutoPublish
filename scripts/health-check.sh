#!/bin/bash
# Health Check
for i in {1..10}; do
  HTTP_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/health)

  if [ "$HTTP_CODE" == "200" ]; then
    echo "Health check passed"
    exit 0
  fi

  echo "Attempt $i: HTTP $HTTP_CODE"
  sleep 5
done

echo "Health check failed"
exit 1