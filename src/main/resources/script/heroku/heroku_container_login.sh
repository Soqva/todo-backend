printf "\nChecking if docker is running (if not - it will be started)\n"
sh ../util/start_docker.sh

printf "\nHeroku container login\n"
heroku container:login

printf "\nYou have successfully logged in. Everything is fine\n"
