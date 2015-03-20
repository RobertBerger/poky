rber@main-1-X61s-64-bit:~/projects/various/poky$ git diff dizzy-12.0.1 --stat
 meta/lib/oeqa/runtime/parselogs.py |    8 +
 meta/lib/oeqa/utils/qemurunner.py  |    7 +-
 my-scripts/doc/readme.txt          |   11 +
 my-scripts/push-all-to-github.sh   |    2 +
 scripts/runqemu                    |    5 +-
 scripts/runqemu-internal           |   38 +-
 scripts/runqemu-internal.3.14      |  693 ++++++++++++++++++++++++++++++++++++
 scripts/runqemu-internal.3.19      |  693 ++++++++++++++++++++++++++++++++++++
 8 files changed, 1452 insertions(+), 5 deletions(-)


