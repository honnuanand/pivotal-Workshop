using Steeltoe.Management.Endpoint.Info;
using Microsoft.Extensions.Options;

namespace CloudFoundry
{
    public class ConfigurationSemanticVersioningInfoContributor : IInfoContributor
    {
        public SemanticVersion Version { get; set; }

        public ConfigurationSemanticVersioningInfoContributor(IOptions<SemanticVersion> version)
        {
            if (version != null)
            {
                Version = version.Value;
            }
        }

        public void Contribute(IInfoBuilder builder)
        {
            builder.WithInfo("semanticVersionFromConfiguration", Version.ToString());
        }
    }
}
