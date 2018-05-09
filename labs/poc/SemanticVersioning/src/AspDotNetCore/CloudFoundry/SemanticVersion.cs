namespace CloudFoundry
{
    public class SemanticVersion
    {
        public int Major { get; set; }
        public int Minor { get; set; }
        public int Patch { get; set; }
        public string PreRelease { get; set; }

        public override string ToString()
        {
            return string.IsNullOrWhiteSpace(PreRelease) ? $"{Major}.{Minor}.{Patch}" : $"{Major}.{Minor}.{Patch}.{PreRelease}";
        }
    }
}
