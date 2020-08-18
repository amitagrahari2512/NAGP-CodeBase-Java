$containerid = docker ps -f name=devops_application
if ($containerid)
{
 	write-host "Container exists | deleting " + $containerid
 	docker container rm -f $containerid
}
