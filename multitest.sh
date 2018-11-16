#!/bin/bash

for algo in {"glouton","progdyn1","progdyn2","recuit"}; do
    echo $algo
    counter=1
    if [ $algo == "recuit" ]; then
        counter=5
    fi
    for (( c=1; c<=$counter; c++ ))
    do
       echo $c
        for serie in $(ls "jeux_de_donnees/WC"); do
            echo ${serie///}
            for ex in $(ls "jeux_de_donnees/WC/$serie"); do
                t=$(./tp.sh -a $algo -e "$(pwd)/jeux_de_donnees/WC/$serie/$ex" -t -p)
            done
        done
    done
done
$(python.exe graphScript.py "$(pwd)/resultats/results.csv")