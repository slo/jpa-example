call mvn -Pliberty-managed clean package 
call mvn -Pliberty-managed liberty:create liberty:install-feature
rem liberty:deploy 
call mvn -Pliberty-managed liberty:configure-arquillian 
rem call mvn -Pliberty-managed failsafe:integration-test 
