@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    Page<Ad> findAllByOrderBySubmissionTimeDesc(Pageable pageable);
}
