cmake_minimum_required(VERSION 3.22.1)
project("getevenlucky")

# Add the native library
add_library(native-lib SHARED getevenlucky.cpp)

find_library(log-lib log)
find_library(android-lib android)

target_link_libraries(native-lib
        ${log-lib}
        ${android-lib})