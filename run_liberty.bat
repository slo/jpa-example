call mvn -Pliberty-managed clean package 
call mvn -Pliberty-managed liberty:create liberty:install-feature liberty:deploy 
call mvn -Pliberty-managed liberty:configure-arquillian 
call mvn -Pliberty-managed failsafe:integration-test 
