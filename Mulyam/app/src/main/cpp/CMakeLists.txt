cmake_minimum_required(VERSION 3.22.1)
project("mulyam")
add_library(KeyProvider SHARED KeyProvider.cpp)
find_library(log-lib log)
target_link_libraries(KeyProvider ${log-lib})