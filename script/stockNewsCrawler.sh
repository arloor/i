#! /bin/bash
export LANG=zh_CN.UTF-8
cd /root/java/stockNewsCrawler/
rm -rf news
java -jar Crawler.jar
