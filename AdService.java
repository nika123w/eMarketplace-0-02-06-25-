@Service
public class AdService {
    @Autowired
    private AdRepository adRepository;

    public Page<AdResponseDto> getAllAds(int page) {
        Pageable pageable = PageRequest.of(page, 6, Sort.by("submissionTime").descending());
        return adRepository.findAll(pageable).map(this::toDto);
    }

    public AdResponseDto getAdById(Long id) {
        return adRepository.findById(id).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Ad not found"));
    }

    public AdResponseDto createAd(AdRequestDto dto, String photoUrl) {
        Ad ad = new Ad();
        ad.setName(dto.getName());
        ad.setPrice(dto.getPrice());
        ad.setDescription(dto.getDescription());
        ad.setSubmissionTime(LocalDateTime.now());
        ad.setPhotoUrl(photoUrl);

        return toDto(adRepository.save(ad));
    }

    private AdResponseDto toDto(Ad ad) {
        AdResponseDto dto = new AdResponseDto();
        dto.setId(ad.getId());
        dto.setName(ad.getName());
        dto.setPrice(ad.getPrice());
        dto.setDescription(ad.getDescription());
        dto.setSubmissionTime(ad.getSubmissionTime());
        dto.setPhotoUrl(ad.getPhotoUrl());
        return dto;
    }
}
