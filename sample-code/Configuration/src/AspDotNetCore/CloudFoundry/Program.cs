
using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;
using Steeltoe.Extensions.Configuration.CloudFoundry;

namespace CloudFoundry
{
    public class Program
    {

        public static void Main(string[] args)
        {
            BuildWebHost(args).Run();
        }
        public static IWebHost BuildWebHost(string[] args) =>
            WebHost.CreateDefaultBuilder(args)

                    // Allows the application to run on a port set by CloudFoundry at runtime.
                    .UseCloudFoundryHosting()

                    // For VCAP Services...puhses onto configuration
                    .AddCloudFoundry()
                    .UseStartup<Startup>()
                    .Build();

    }
}
