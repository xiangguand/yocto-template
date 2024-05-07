#!/bin/bash
# Script to build image for qemu.
# Author: Siddhant Jajoo.

# Change the poky to specific branch
cd poky
git checkout kirkstone
cd ..

# local.conf won't exist until this step on first execution
source poky/oe-init-build-env

CONFLINE="MACHINE = \"qemuarm64\""

cat conf/local.conf | grep "${CONFLINE}" > /dev/null
local_conf_info=$?

if [ $local_conf_info -ne 0 ];then
	echo "Append ${CONFLINE} in the local.conf file"
	echo ${CONFLINE} >> conf/local.conf
	
else
	echo "${CONFLINE} already exists in the local.conf file"
fi


bitbake-layers show-layers | grep "meta-helloworld" > /dev/null
layer_info=$?

if [ $layer_info -ne 0 ];then
	echo "Adding meta-helloworld layer"
	bitbake-layers add-layer ../meta-helloworld
else
	echo "meta-helloworld layer already exists"
fi

set -e
bitbake core-image-helloworld
