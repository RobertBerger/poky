SUMMARY = "Userspace NFS server v3 protocol"
SECTION = "console/network"
LICENSE = "unfs3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9475885294e17c0cc0067820d042792e"

# SVN support for upstream version check isn't implemented yet
RECIPE_UPSTREAM_VERSION = "0.9.22.r497"
RECIPE_UPSTREAM_DATE = "Oct 08, 2015"
CHECK_DATE = "Dec 10, 2015"

DEPENDS = "flex-native bison-native flex"
DEPENDS_append_libc-musl = " libtirpc"
DEPENDS_append_class-nativesdk = " flex-nativesdk"

MOD_PV = "497"
S = "${WORKDIR}/trunk"
# Only subversion url left in OE-Core, use a mirror tarball instead since
# this rarely changes.
# svn://svn.code.sf.net/p/unfs3/code;module=trunk;rev=${MOD_PV};protocol=http
SRC_URI = "http://downloads.yoctoproject.org/mirror/sources/trunk_svn.code.sf.net_.p.unfs3.code_497_.tar.gz \
           file://unfs3_parallel_build.patch \
           file://alternate_rpc_ports.patch \
           file://fix_pid_race_parent_writes_child_pid.patch \
           file://fix_compile_warning.patch \
           file://rename_fh_cache.patch \
           file://relative_max_socket_path_len.patch \
           file://tcp_no_delay.patch \
          "

SRC_URI[md5sum] = "db06a7cdb1c0752d12f45be4e5520d5d"
SRC_URI[sha256sum] = "0872a96910d1df779390f74375cb50cd2df1c5d9d1ffb016aff596384fdff409"

BBCLASSEXTEND = "native nativesdk"

inherit autotools
EXTRA_OECONF_append_class-native = " --sbindir=${bindir}"
CFLAGS_append_libc-musl = " -I${STAGING_INCDIR}/tirpc"

# Turn off these header detects else the inode search
# will walk entire file systems and this is a real problem
# if you have 2 TB of files to walk in your file system
CACHED_CONFIGUREVARS = "ac_cv_header_mntent_h=no ac_cv_header_sys_mnttab_h=no"
