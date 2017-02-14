---
git fetch url-to-repo branchname:refs/remotes/origin/branchname

git fetch git://git.yoctoproject.org/poky dizzy:refs/remotes/origin/dizzy
---
>> git remote -v

official-upstream       git://git.yoctoproject.org/poky (fetch)
official-upstream       git://git.yoctoproject.org/poky (push)
origin  git@github.com:RobertBerger/poky.git (fetch)
origin  git@github.com:RobertBerger/poky.git (push)

>> git fetch official-upstream
remote: Counting objects: 4043, done.
remote: Compressing objects: 100% (1273/1273), done.
remote: Total 4043 (delta 3130), reused 3632 (delta 2727)
Receiving objects: 100% (4043/4043), 721.50 KiB | 402.00 KiB/s, done.
Resolving deltas: 100% (3130/3130), completed with 502 local objects.
From git://git.yoctoproject.org/poky
   62591d9..e758547  master     -> official-upstream/master
 + 2942327...a382678 master-next -> official-upstream/master-next  (forced update)
   a3fa5ce..6a1f33c  morty      -> official-upstream/morty
---
