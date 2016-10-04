poky
====

fork of poky - sorry don't know how else to fix runqemu stuff

Please advise how to fix this!

For now I'll keep it, since the Yocto guys and gals change randomly what's on their release branches and I don't like surprises ;)

--------------

git clone git@github.com:RobertBerger/poky.git
cd poky
git remote add official-upstream git://git.yoctoproject.org/poky
git fetch official-upstream
git branch -a

--------------
