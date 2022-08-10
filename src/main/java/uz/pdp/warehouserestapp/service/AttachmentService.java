package uz.pdp.warehouserestapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouserestapp.entity.Attachment;
import uz.pdp.warehouserestapp.entity.AttachmentContent;
import uz.pdp.warehouserestapp.repository.AttachmentContentRepository;
import uz.pdp.warehouserestapp.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    private final AttachmentRepository repository;
    private final AttachmentContentRepository contentRepository;

    @Autowired
    public AttachmentService(AttachmentRepository repository, AttachmentContentRepository contentRepository) {
        this.repository = repository;
        this.contentRepository = contentRepository;
    }

    public ResponseEntity<?> uploadAttachment(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            Attachment attachment = new Attachment(file.getOriginalFilename(), file.getSize(), file.getContentType());
            try {
                attachment = repository.save(attachment);
                AttachmentContent content = new AttachmentContent(file.getBytes(), attachment);
                contentRepository.save(content);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> downloadAttachment(Integer id, HttpServletResponse response) {
        Optional<Attachment> optionalAttachment = repository.findById(id);
        if (!optionalAttachment.isPresent()) return new ResponseEntity<>("File Not Found", HttpStatus.NOT_FOUND);
        Attachment attachment = optionalAttachment.get();
        Optional<AttachmentContent> optional = contentRepository.findByAttachmentId(attachment.getId());
        if (!optional.isPresent()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"");
        response.setContentType(attachment.getContentType());
        try {
            FileCopyUtils.copy(optional.get().getBytes(), response.getOutputStream());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
