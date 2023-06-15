/**
 * 
 */
package tw.hyin.demo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YingHan 2022-03-08
 */
@Data
public class MailContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String from;
	private String[] to;
	private String[] cc;
	private String subject;
	private String content;

}
