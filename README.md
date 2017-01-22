# giskis
GisKis stores the information from kismet in a database so that the user can generate graphs using SQL.

# How to build:

 cd <project-root>
 export GOPATH=<project-root>/
 cd <project-root>/src/giskis/
 go get
 go build
 
# How to execute
After building the executable:
 $> ./giskis <path-to-kismet-netxml>.netxml
