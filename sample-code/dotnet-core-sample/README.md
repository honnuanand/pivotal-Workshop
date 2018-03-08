# CF Sample App .NET Core

A sample [.NET Core](https://www.microsoft.com/net/download/windows) application to deploy to Cloud Foundry which works out of the box.

## Run locally

1. Install [.NET Core](https://www.microsoft.com/net/learn/get-started/windows)
1. run 'dotnet restore'
1. run 'dotnet publish -f netcoreapp2.0 -r ubuntu.14.04-x64'
1. run 'dotnet run'
1. Visit <http://localhost:5000>

## Run in the cloud

1. Run 'cf push -f .\manifest.yml -p .\bin\Debug\netcoreapp2.0\ubuntu.14.04-x64\publish'
1. Visit the given URL
