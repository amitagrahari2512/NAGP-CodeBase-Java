$containerid = docker ps -f name=devops_application
if ($containerid)
{
 	write-host "Container exists | deleting"
 	docker rm -f $containerid
}
