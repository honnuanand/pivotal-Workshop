using Steeltoe.Management.Endpoint.Info;
using System.Reflection;
using System.Diagnostics;
using System.Collections.Generic;

namespace CloudFoundry
{
    public class AssemblySemanticVersioningInfoContributor : IInfoContributor
    {
        public void Contribute(IInfoBuilder builder)
        {
            //  Assembly version, this is what .net uses for loading assemblies
            string assemblyVersion = Assembly.GetExecutingAssembly().GetName().Version.ToString();

            //  Assembly flie version, this is what is reported to windows explorer, etc.
            string fileVersion = FileVersionInfo.GetVersionInfo(Assembly.GetExecutingAssembly().Location).FileVersion;

            //  Assuming you want the product version...this is what Nuget uses
            string productVersion = FileVersionInfo.GetVersionInfo(Assembly.GetExecutingAssembly().Location).ProductVersion;

            var builderDictionary = new Dictionary<string, object>
            {
                { "assemblyVersion", assemblyVersion },
                { "fileVersion", fileVersion },
                { "productVersion", productVersion }
            };

            builder.WithInfo(builderDictionary);
        }
    }
}
