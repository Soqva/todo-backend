sh ./heroku_container_login.sh

cd "D:/Projects/todo-backend"

printf "\nPushing the application to the Heroku\n"
heroku container:push web

printf "\nRelease the deployed app\n"
heroku container:release web

printf "\nEnabling the application\n"
heroku ps:scale web=1

printf "\nTurn on logs of the application\n"
heroku logs --tail


