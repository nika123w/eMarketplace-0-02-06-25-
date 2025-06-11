@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private AdService adService;

    @GetMapping
    public Page<AdResponseDto> getAllAds(@RequestParam(defaultValue = "0") int page) {
        return adService.getAllAds(page);
    }

    @GetMapping("/{id}")
    public AdResponseDto getAdById(@PathVariable Long id) {
        return adService.getAdById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AdResponseDto createAd(@RequestPart("ad") AdRequestDto adRequest,
                                  @RequestPart("file") MultipartFile file) throws IOException {
        String photoUrl = FileUploadUtil.saveFile(file);
        return adService.createAd(adRequest, photoUrl);
    }
}
