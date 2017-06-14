#! /bin/bash
export LANG=zh_CN.UTF-8
cd /root/java/pocoPhotoCrawler
java -jar PocoImageCrawler.jar
cp pocoImg/* /var/www/i/pocoImg/
