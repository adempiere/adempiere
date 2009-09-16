#!/bin/sh
#
# CheckConflicts.sh
# Carlos Ruiz - globalqss
# Check for conflicts between files patches, packages and customization (same for zk)

PROCESS=$$
TMPDIR=/tmp
TMPLISADEMPIEREORIGINAL=$TMPDIR/lisadempiereoriginal$PROCESS.txt
TMPLISWEBUIORIGINAL=$TMPDIR/liswebuioriginal$PROCESS.txt
TMPLISPATCHES=$TMPDIR/lispatches$PROCESS.txt
TMPLISCUST=$TMPDIR/liscustomization$PROCESS.txt
TMPLISPACK=$TMPDIR/lispackages$PROCESS.txt
TMPLISALLPACK=$TMPDIR/lisallpackages$PROCESS.txt
TMPLISUNQPACK=$TMPDIR/lisunqpackages$PROCESS.txt
TMPLISZKPATCHES=$TMPDIR/liszkpatches$PROCESS.txt
TMPLISZKCUST=$TMPDIR/liszkcustomization$PROCESS.txt
TMPLISZKPACK=$TMPDIR/liszkpackages$PROCESS.txt
TMPLISZKALLPACK=$TMPDIR/liszkallpackages$PROCESS.txt
TMPLISZKUNQPACK=$TMPDIR/liszkunqpackages$PROCESS.txt

jar tf AdempiereOriginal.jar |
    fgrep -v META-INF |
    fgrep .class |
    sort > $TMPLISADEMPIEREORIGINAL

jar tf webuiOriginal.war |
    fgrep -v META-INF |
    fgrep .class |
    sort > $TMPLISWEBUIORIGINAL

jar tf patches.jar |
    fgrep -v META-INF |
    fgrep .class |
    sort > $TMPLISPATCHES

jar tf customization.jar |
    fgrep -v META-INF |
    fgrep .class |
    sort > $TMPLISCUST

jar tf packages.jar |
    fgrep -v META-INF |
    fgrep .class |
    sort > $TMPLISPACK

> $TMPLISALLPACK
for JARFILE in `ls ../packages/*/lib/*.jar 2> /dev/null`
do
    jar tf $JARFILE |
        fgrep -v META-INF |
        fgrep .class >> $TMPLISALLPACK
done
sort -o $TMPLISALLPACK $TMPLISALLPACK
sort -u -o $TMPLISUNQPACK $TMPLISALLPACK

jar tf zkpatches.jar |
    fgrep -v META-INF |
    fgrep ".class
.css.dsp" |
    sort > $TMPLISZKPATCHES

jar tf zkcustomization.jar |
    fgrep -v META-INF |
    fgrep ".class
.css.dsp" |
    sort > $TMPLISZKCUST

jar tf zkpackages.jar |
    fgrep -v META-INF |
    fgrep ".class
.css.dsp" |
    sort > $TMPLISZKPACK

> $TMPLISZKALLPACK
for JARFILE in `ls ../zkpackages/*/lib/*.jar 2> /dev/null`
do
    jar tf $JARFILE |
        fgrep -v META-INF |
        fgrep ".class
.css.dsp" >> $TMPLISZKALLPACK
done
sort -o $TMPLISZKALLPACK $TMPLISZKALLPACK
sort -u -o $TMPLISZKUNQPACK $TMPLISZKALLPACK


if [ `comm -12 $TMPLISPACK $TMPLISADEMPIEREORIGINAL | wc -l` -ne 0 ]
then
    echo "** WARNING: Package classes are overwritting Adempiere classes. Package will take precedence"
    comm -12 $TMPLISPACK $TMPLISADEMPIEREORIGINAL
fi

if [ `comm -12 $TMPLISZKPACK $TMPLISWEBUIORIGINAL | wc -l` -ne 0 ]
then
    echo "** WARNING: ZK Package classes are overwritting ZK webui original classes. ZK Package will take precedence"
    comm -12 $TMPLISZKPACK $TMPLISWEBUIORIGINAL
fi

if [ `comm -12 $TMPLISPATCHES $TMPLISCUST | wc -l` -ne 0 ]
then
    echo "** WARNING: Dup files in customization and patches.  Customization will take precedence"
    comm -12 $TMPLISPATCHES $TMPLISCUST
fi

if [ `comm -12 $TMPLISPACK $TMPLISCUST | wc -l` -ne 0 ]
then
    echo "** WARNING: Dup files in customization and packages.  Customization will take precedence"
    comm -12 $TMPLISPACK $TMPLISCUST
fi

if [ `comm -12 $TMPLISPATCHES $TMPLISPACK | wc -l` -ne 0 ]
then
    echo "** WARNING: Dup files in packages and patches.  Packages will take precedence"
    comm -12 $TMPLISPATCHES $TMPLISPACK
fi

if [ `comm -23 $TMPLISALLPACK $TMPLISUNQPACK | wc -l` -ne 0 ]
then
    echo "** WARNING: Dup files in several files on packages directory.  One of them will take precedence (uncertain)"
    comm -23 $TMPLISALLPACK $TMPLISUNQPACK
fi

if [ `comm -12 $TMPLISZKPATCHES $TMPLISZKCUST | wc -l` -ne 0 ]
then
    echo "** WARNING: Dup files in zkcustomization and zkpatches.  ZK Customization will take precedence"
    comm -12 $TMPLISZKPATCHES $TMPLISZKCUST
fi

if [ `comm -12 $TMPLISZKPACK $TMPLISZKCUST | wc -l` -ne 0 ]
then
    echo "** WARNING: Dup files in zkcustomization and zkpackages.  ZK Customization will take precedence"
    comm -12 $TMPLISZKPACK $TMPLISZKCUST
fi

if [ `comm -12 $TMPLISZKPATCHES $TMPLISZKPACK | wc -l` -ne 0 ]
then
    echo "** WARNING: Dup files in zkpackages and zkpatches.  ZK Packages will take precedence"
    comm -12 $TMPLISZKPATCHES $TMPLISZKPACK
fi

if [ `comm -23 $TMPLISZKALLPACK $TMPLISZKUNQPACK | wc -l` -ne 0 ]
then
    echo "** WARNING: Dup files in several files on zkpackages directory.  One of them will take precedence (uncertain)"
    comm -23 $TMPLISZKALLPACK $TMPLISZKUNQPACK
fi

rm -f $TMPLISADEMPIEREORIGINAL $TMPLISWEBUIORIGINAL $TMPLISPATCHES $TMPLISCUST $TMPLISPACK $TMPLISALLPACK $TMPLISUNQPACK $TMPLISZKPATCHES $TMPLISZKCUST $TMPLISZKPACK $TMPLISZKALLPACK $TMPLISZKUNQPACK
