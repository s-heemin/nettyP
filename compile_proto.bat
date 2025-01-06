@echo off

rmdir /s /q "growstone-network\src\main\java\com\supercat\growstone\network\messages"

protoc.exe --java_out=growstone-network\src\main\java Network.proto NetEnum.proto

pause
