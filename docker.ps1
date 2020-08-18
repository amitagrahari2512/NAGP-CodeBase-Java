$containerid = docker ps -f name=devops_application
write-host $containerid
if ($containerid)
{
 	write-host "Container exists | deleting " 
 	docker container rm -f devops_application
}
