DESCRIPTION="Simple hello world application"
# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# about how to setup ssh-agent for passwordless access
SRC_URI = "file://hello.c"
S = "${WORKDIR}"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "server" directory here to build from the "server" directory
# in your assignments repo
# S = "${WORKDIR}/git/server"
# INITSCRIPT_NAME = "aesdsocket_service"

# Add the aesdsocket application and any other files you need to install
# See https://git.yoctoproject.org/poky/plain/meta/conf/bitbake.conf?h=kirkstone

# FILES:${PN} += "${bindir}/aesdsocket"
# FILES:${PN} += "${sysconfdir}/init.d"

# customize these as necessary for any libraries you need for your application
# (and remove comment)
# INSANE_SKIP:${PN} += "ldflags"

do_configure () {
	:
}

do_compile () {
	${CC} hello.c ${LDFLAGS} -o helloyocto
}

do_install () {
	# Be sure to install the target directory with install -d first
	# Yocto variables ${D} and ${S} are useful here, which you can read about at 
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-D

	install -d ${D}${bindir}
	install -m 0755 helloyocto ${D}${bindir}
}
