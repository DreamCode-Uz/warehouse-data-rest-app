package uz.pdp.warehouserestapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.warehouserestapp.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    private final AttachmentService service;

    @Autowired
    public AttachmentController(AttachmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> upload(MultipartHttpServletRequest request) {
        return service.uploadAttachment(request);
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<?> download(@PathVariable("fileId") Integer id, HttpServletResponse response) {
        return service.downloadAttachment(id, response);
    }
}
