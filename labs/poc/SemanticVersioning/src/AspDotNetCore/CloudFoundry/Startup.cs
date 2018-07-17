using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Steeltoe.CloudFoundry.Connector.MySql;
using Steeltoe.Management.CloudFoundry;
using Steeltoe.Management.Endpoint.Health;
using Steeltoe.Management.Endpoint.Info;

namespace CloudFoundry
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddOptions();

            services.AddMySqlConnection(Configuration);

            // Add custom health check contributor
            services.AddScoped<IHealthContributor, MySqlHealthContributor>();

            // Add SemanticVersioningContributor
            services.AddSingleton<IInfoContributor, ConfigurationSemanticVersioningInfoContributor>();
            services.AddSingleton<IInfoContributor, AssemblySemanticVersioningInfoContributor>();

            // Add managment endpoint services
            services.AddCloudFoundryActuators(Configuration);

            // Add framework services.
            services.AddMvc();

            var versionSection = Configuration.GetSection("version");
            services.Configure<SemanticVersion>(versionSection);
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env, ILoggerFactory loggerFactory)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
            }

            app.UseStaticFiles();

            // Add management endpoints into pipeline
            app.UseCloudFoundryActuators();

            app.UseMvc(routes =>
            {
                routes.MapRoute(
                    name: "default",
                    template: "{controller=Home}/{action=Index}/{id?}");
            });
        }
    }
}
