SUMMARY = "inittab configuration for BusyBox"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://inittab"

S = "${WORKDIR}"

INHIBIT_DEFAULT_DEPS = "1"

do_compile() {
	:
}

do_install() {
	install -d ${D}${sysconfdir}
	install -D -m 0644 ${WORKDIR}/inittab ${D}${sysconfdir}/inittab

    CONSOLES="${SERIAL_CONSOLES}"
    for s in $CONSOLES
    do
        speed=$(echo $s | cut -d\; -f 1)
        device=$(echo $s | cut -d\; -f 2)
        label=$(echo $device | sed -e 's/tty//' | tail --bytes=5)

        echo "$device::respawn:${sbindir}/ttyrun $device ${base_sbindir}/getty $speed $device" >> ${D}${sysconfdir}/inittab
    done

	if [ "${USE_VT}" = "1" ]; then
		cat <<EOF >>${D}${sysconfdir}/inittab
# ${base_sbindir}/getty invocations for the runlevels.
#
# The "id" field MUST be the same as the last
# characters of the device (after "tty").
#
# Format:
#  <id>:<runlevels>:<action>:<process>
#

EOF

		for n in ${SYSVINIT_ENABLED_GETTYS}
		do
			echo "tty$n:12345:respawn:${base_sbindir}/getty 38400 tty$n" >> ${D}${sysconfdir}/inittab
		done
		echo "" >> ${D}${sysconfdir}/inittab
	fi

}

pkg_postinst:${PN} () {
# run this on host and on target
if [ "${SERIAL_CONSOLES_CHECK}" = "" ]; then
       exit 0
fi
}

pkg_postinst_ontarget:${PN} () {
# run this on the target
if [ -e /proc/consoles ]; then
        tmp="${SERIAL_CONSOLES_CHECK}"
        for i in $tmp
        do
                j=`echo ${i} | sed -e s/^.*\;//g -e s/\:.*//g`
                k=`echo ${i} | sed s/^.*\://g`
                if [ -z "`grep ${j} /proc/consoles`" ]; then
                        if [ -z "${k}" ] || [ -z "`grep ${k} /proc/consoles`" ] || [ ! -e /dev/${j} ]; then
                                sed -i -e /^.*${j}\ /d -e /^.*${j}$/d /etc/inittab
                        fi
                fi
        done
        kill -HUP 1
else
        exit 1
fi
}

# SERIAL_CONSOLES is generally defined by the MACHINE .conf.
# Set PACKAGE_ARCH appropriately.
PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} = "${sysconfdir}/inittab"
CONFFILES:${PN} = "${sysconfdir}/inittab"

RDEPENDS:${PN} = "ttyrun"
RCONFLICTS:${PN} = "sysvinit-inittab"

USE_VT ?= "1"
SYSVINIT_ENABLED_GETTYS ?= "1"
