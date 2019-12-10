#!/bin/bash
echo "AUTOMATED SCRIPT FOR STANDARD TEST EXECUTION"
echo "==============================================="
echo "Available Emulators"
~/Library/Android/sdk/tools/emulator -avd -list-avds
echo "Enter Emulator to Start"
read emulator_name
echo "==============================================="
echo "Starting Emulator ($emulator_name) in the background"
~/Library/Android/sdk/tools/emulator @$emulator_name &
echo "Emulator Information"
sleep 6
if ! type adb; then
echo "adb not found"
echo "check PATH"
else
echo "============================"
echo "Android Device Specificatios"
echo "============================"
adb wait-\for-device
echo "> Manufacturer"
adb shell getprop ro.product.manufacturer
echo "> Name"
adb shell getprop ro.product.name
echo "> Model"
adb shell getprop ro.product.model
echo "----------------------------"
fi

echo "==============================================="
echo "Starting tests"
