# library-management
Library Management System. A simple CRUD example of a Library Management System. This project is for learning perpose.

# Testing
# http://localhost:8088/api/rest/lms/borrower/getbyid?borrowerid=1001
# http://localhost:8088/api/rest/lms/borrower/getbyname/Rajib Kumer Ghosh
# POST http://localhost:8080/api/rest/lms/borrower/createborrower
# {
# "borrowerid":"1",
# "name": "Rajib Kumer Ghosh",
# "email": "rajib@gmail.com"
# }

# PUT http://localhost:8080/api/rest/lms/borrower/updateborrower
# {
# "borrowerid":"1",
# "name": "Rajib Kumer Ghosh",
# "email": "rajib@gmail.com"
# }

# PATCH http://localhost:8080/api/rest/lms/borrower/updateborroweremail/1001
# {
# "name": "Rajib Kumer Ghosh"
# }

# PATCH http://localhost:8080/api/rest/lms/borrower/updateborrowerpartial/1001
# {
# "name": "Rajib Kumer Ghosh",
# "email": "rajib@gmail.com"
# }

# DELETE http://localhost:8080/api/rest/lms/borrower/deleteborrower
# {
# "borrowerid":"1",
# "name": "Rajib Kumer Ghosh",
# "email": "rajib@gmail.com"
# }

# Create a borrower
# curl -X POST "http://localhost:8080/api/rest/lms/borrower/createborrower" -H "Content-Type: application/json" -d '{"name": "Rajib Kumer Ghosh", "email": "rajib@gmail.com"}'

# Get All Borrowers
# curl -X GET "http://localhost:8080/api/rest/lms/borrower"

# Get Borrower by ID
# curl -X GET "http://localhost:8080/api/rest/lms/borrower/1"

# Delete Borrower
# curl -X DELETE "http://localhost:8080/api/rest/lms/borrower/1"


# GitHub
# Create a new Repository in GitHub with name =
# git init
# git add .
# git commit -m "Initial commit"
# git remote add origin <remote-repositories-URL>
# git remote add origin https://github.com/rajhimaloy/library-management.git
# git branch -M main  # Rename the branch to 'main' (optional)
# git push -u origin main
# git push -u origin main --force
# OR
# git pull --rebase origin main
# git add .
# git rebase --continue
# git push -u origin main

# To add new file and modified file to remote(GitHub) existing Repository (Default Main Branch)
# git pull
# git add docker-compose-tools.yaml
# git add Dockerfile
# git commit -a -m "Docker tools added"
# git push

# To verify that your files are added and committed, run:
# git status    # Check untracked/modified files
# git log       # View commit history
# git remote -v # Check remote repositories
# git remote 	# Check remote repositories branch