$containerid = docker ps -f name=devops_application
if ($containerid)
{
 	write-host "Container exists | deleting"
 	docker container stop $containerid
 	docker container rm  $containerid
}
