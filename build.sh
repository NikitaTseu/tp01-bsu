#!/bin/bash

mkdir -p classes
rm -rf classes/*

cd src
javac -cp . *.java */*.java -d ../classes
cd ..
jar cvfe myPaint.jar Main -C classes .

