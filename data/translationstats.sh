#!/bin/bash
if [ $# -lt 1 ]; then
  echo "usage: $0 <languagedir>"
  echo "you may for example call $0 de_DE/"
  exit 1 
fi

for dir in $@; do
  total=0;
  translated=0;
  echo -n "$dir: "
  if [ -d $dir ]; then
    cd $dir;
    for file in *.xml; do
      grep value $file | sed 's:.*<.*original="\(.*\)">\(.*\)<.*:\1 #### \2:' | sed 's:.*<.*original="\(.*\)"/>:\1 #### :' > $file.tmp
      while read line; do
        orig=${line% ####*}
        trans=${line#*#### }
        # not even the original has a translation, therefore we do not care
        if [ "$orig" == "####" ]; then
          continue;
        fi
        total=$((total + 1))
        # one cannot depend on the trl="Y" attribute, therefore we are checking whether the translation differs from the original
        if [ "$orig" != "$trans" ]; then
          translated=$((translated+1))
        fi
      done < $file.tmp
      rm $file.tmp
    done
    cd ..
    echo "$translated strings are translated. The files contain $total strings."
  fi
done
