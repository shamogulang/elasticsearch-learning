#!/bin/bash
# docker inspect --format '{{.LogPath}}' containerId
# docker logs containerId
portMapping=$1
dockerImage=$2
imageVersion=$3
containerName=$4
activeProfile=$5
logDir0=$6
containerCnt=$7

# ifconfig eth0 | grep "inet " | awk '{print $2}' 获取ip地址
# ifconfig eth0 | grep "inet " | awk '{print $2}' | awk -F '.' '{print $NF}'

containerId=`docker ps -a| grep $dockerImage | awk '{print $1}'`

ipNum=`ifconfig eth0 | grep "inet " | awk '{print $2}' | awk -F '.' '{print $NF%10}'`

echo " remove image=$dockerImage container,  id=$containerId ..."

docker rm -f $containerId

echo " start pull image $dockerImage ..."

docker pull $dockerImage:$imageVersion

echo " starting image $dockerImage container ..."

while (( $containerCnt > 0 ))
do
ipNum=ipNum+10

containIp="174.17.0.$ipNum"

containerNamePlus=$containerName+$containerCnt

docker run -d -e ACTIVE_PROFILE=$activeProfile --network=search --ip $containIp  -v $logDir0  -p $portMapping --name $containerNamePlus   $dockerImage:$imageVersion

containerCnt=$containerCnt-1
done

docker ps