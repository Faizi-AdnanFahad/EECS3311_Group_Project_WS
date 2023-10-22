@echo off
set CONFIG_FILE="config.sqlite3"

echo "Starting setup"
echo "Running our setup script"

sqlite3 < %CONFIG_FILE%

echo "Press any key to exit..."
pause