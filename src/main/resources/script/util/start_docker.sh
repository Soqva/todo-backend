if (! docker stats --no-stream &> /dev/null); then
  printf "\nA Docker Desktop is not running. It is being started now\n"
  start "" "C:/Program Files/Docker/Docker/Docker Desktop.exe"
  while (! docker stats --no-stream &> /dev/null); do
    printf "Waiting for Docker to launch...\n"
    sleep 5
  done
  printf "\nThe Docker Desktop has started successfully\n"
  exit 0
fi

printf "\nA Docker Desktop is running\n"
