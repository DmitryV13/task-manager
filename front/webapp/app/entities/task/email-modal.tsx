import React, { useState } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Input, Label } from 'reactstrap';
import { useAppDispatch } from '../../config/store';
import { sendSimpleMail } from './task.reducer';

const EmailModal = () => {
    const [modal, setModal] = useState(false);
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');

    const toggle = () => setModal(!modal);

    const dispatch = useAppDispatch();
    const handleSubmit = () => {
        const mailData = { to: email, msg: message };
        dispatch(sendSimpleMail(mailData));
        toggle()
    };

    return (
        <div>
            <Button className="me-2" color="warning" onClick={toggle}>
                Send email
            </Button>

            <Modal isOpen={modal} toggle={toggle}>
                <ModalHeader toggle={toggle}>Send Email</ModalHeader>
                <ModalBody>
                    <Label for="email">To:</Label>
                    <Input
                        id="email"
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="email@email.com"
                    />

                    <Label for="message" className="mt-2">Message:</Label>
                    <Input
                        id="message"
                        type="textarea"
                        value={message}
                        onChange={(e) => setMessage(e.target.value)}
                        placeholder="Write message..."
                    />
                </ModalBody>
                <ModalFooter>
                    <Button color="primary" onClick={handleSubmit}>
                        Send
                    </Button>
                    <Button color="secondary" onClick={toggle}>
                        Cancel
                    </Button>
                </ModalFooter>
            </Modal>
        </div>
    );
};

export default EmailModal;
